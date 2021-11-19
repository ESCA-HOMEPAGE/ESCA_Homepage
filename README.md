# ESCA_HomePage
만화 동아리 ESCA의 홈페이지 제작



### 개발일지
- 11/13 ~ 11/19: mybatis 에러가 났었음
    - 해결 방법: mapper.xml 파일들 중 안 쓰고 있던 xml파일을 지움
    - 원인:     미리 만들어 놓은 xml파일 안에 있는 빈 bean으로 인해 에러가 발생하였던 것이었음
                log에는 빈 bean이 있는 xml파일에 대한 log가 찍히지 않아 원인을 찾는데에 시간이 걸림
