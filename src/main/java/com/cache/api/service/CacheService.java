package com.cache.api.service;

import com.cache.api.dto.CategoryDto;
import com.cache.api.dto.ProductDto;
import com.cache.api.model.Category;
import com.cache.api.model.Product;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CacheService implements InitializingBean {

    private static final int CACHE_EXPIRE_MINUTE = 60 * 1000;   // 분단위로 Data reload
    private static final int CAPACITY_PRODUCT_KEY_SIZE = 300;

    //CACHE
    private List<CategoryDto> categoryDtoList = new ArrayList<>();
    private Map<Long, ProductDto> productDtoMap = new HashMap<>();
    private Deque<Long> productNoDeque = new LinkedList<>();

    //ORIGIN DATA
    private Map<Integer, Category> categoryMap = Collections.emptyMap();
    private Map<Integer, List<Product>> productListByCategoryNo = Collections.emptyMap();
    private Map<Long, Product> productMap = Collections.emptyMap();
    private CategoryService categoryService;
    private ProductService productService;

    CacheService(@Autowired CategoryService categoryService,
                 @Autowired ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    /**
     * origin data 의 정합성을 최대한 지원하기 위함으로
     * origin data reload schedule
     */
    @Scheduled(fixedRate = (CACHE_EXPIRE_MINUTE))
    public void loadOriginData() {
        Map<Integer, Category> categoryMap = new HashMap<>();

        List<Category> categories = categoryService.getCategoryList();
        for (Category category : categories) {
            categoryMap.put(category.getCategoryNo(), category);
        }

        synchronized (this.categoryMap) {
            this.categoryMap = categoryMap;
        }

        Map<Long, Product> productMap = new HashMap<>();
        Map<Integer, List<Product>> productListMapByCategoryNo = new HashMap<>();

        List<Product> products = productService.getAllProductList();
        for (Product product : products) {

            productMap.put(product.getProductNo(), product);

            if (!productListMapByCategoryNo.containsKey(product.getCategoryNo())) {
                productListMapByCategoryNo.put(product.getCategoryNo(), new ArrayList<>());
            }
            productListMapByCategoryNo.get(product.getCategoryNo()).add(product);

        }

        synchronized (this.productMap) {
            this.productMap = productMap;
        }
        synchronized (this.productListByCategoryNo) {
            this.productListByCategoryNo = productListMapByCategoryNo;
        }
    }

    @Override
    public void afterPropertiesSet() {
        loadOriginData();
    }

    public List<CategoryDto> getCategoryDtoList() {

        if (!categoryDtoList.isEmpty()) {
            return categoryDtoList;
        }

        List<CategoryDto> categoryDtos = new ArrayList<>();

        List<Category> categories = new ArrayList(categoryMap.values());
        for (Category category : categories) {
            CategoryDto categoryDto = CategoryDto.create(category);
            for (Category childCategory : categories) {
                if (childCategory.getParentNo() != null && category.getCategoryNo() == childCategory.getParentNo()) {
                    childCategory.setCategoryName(category.getCategoryName() + "_" + childCategory.getCategoryName());
                }
            }
            categoryDtos.add(categoryDto);
        }
        this.categoryDtoList = categoryDtos;

        return this.categoryDtoList;
    }

    /**
     * 캐쉬 사용에 있어 가장 현실적인 문제는 memory 의 capacity 에 있다고 생각하여
     * 가용한 용량을 product dto 300 개로 임의로 정의함.
     * LRU 방식을 차용하여 조회시점이 가장 오래된 데이터부터 제거하는 방식을 지원하기 위해 deque 사용
     */
    public ProductDto getProductDto(long productNo) {

        if (productDtoMap.containsKey(productNo)) {
            // in cache
            productNoDeque.remove(productNo);

        } else {
            // missing cache
            Product product = productMap.get(productNo);
            ProductDto productDto = null;
            for (CategoryDto categoryDto : getCategoryDtoList()) {
                if (categoryDto.getCategoryNo() == product.getCategoryNo()) {
                    productDto = ProductDto.create(categoryDto.getCategoryNo(), categoryDto.getCategoryName(), product);
                    break;
                }
            }
            productDtoMap.put(productNo, productDto);

            if (productNoDeque.size() == CAPACITY_PRODUCT_KEY_SIZE) {
                Long removeProductNo = productNoDeque.removeLast();
                productDtoMap.remove(removeProductNo);
            }
        }

        productNoDeque.push(productNo);
        return productDtoMap.get(productNo);
    }

    public List<ProductDto> getProductDtoListByCategoryNo(int categoryNo) {
        List<Product> productList = productListByCategoryNo.get(categoryNo);
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product : productList) {
            productDtos.add(getProductDto(product.getProductNo()));
        }

        return productDtos;
    }

}
