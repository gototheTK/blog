# 메이븐이란?
    자바 프로젝트를 빌드하고 배포할수있도록 도와주는 빌드툴이다.
# Get 요청

    특징 : bodt로 데이터를 담아 보내지 않는다.

# Post, Put, Delete 요청 (데이터를 변경)
    - 데이터를 담아보내야 할 것이 많을 때 사용
    - form 태그 method='Post'
    - form 태그 -> get요청, post요청 (key=value)
    - 자바스크립트로 요청을 해야함. 자바스크립트로 ajax요청 + 데이터는 json으로 통일
    - form:form 태그 -> post요청, pust요청, delete요청, get요청

# 트랜잭션
    - 일이 처리되기 위한 가장 작은 단위

# MySQL -> 격리수준
    MySQL- InnoDB 스타리지 엔진 -> Repeatable read 이상 사용 -> 부정합 발생x



# 스프링 객체 순서
    model -> modelRepository -> service -> controller