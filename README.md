### 캐쉬서비스
# 개발 환경
1. spring boot
2. H2
3. JPA
4. gradle build
5. swagger 

# CACHE EVICTION
1. LRU
    1. deque 를 활용
    2. 캐쉬 용량을 넘어서는 경우
    3. 최근에 조회된 데이터가 아닌 데이터를 실시간으로 삭제하여 조정
    
2. 데이터 정합성을 위해 Origin 데이터 리로드
    1. spring boot schedule 활용
    2. 특정 시간마다 origin data reload
    3. cacheService 에서 origin 데이터 적재

# 참고
1. test 를 위하여 swagger 적용
2. origin 데이터 CRUD 를 위해 admin controller 활용
3. 사전과제에 대한 안내를 받지 못하였던터라 급하게 기능구현에 집중하여 테스트 코드 작성 등에 미흡함에 양해를 구합니다.