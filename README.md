# SPRING-BOOT 게시판 만들기

### 날짜별 작업 진행 과정
#### 2020 10월 2일
##### 전체흐름  view -> dto -> controller -> entity -> repository -> JPA -> DB
>게시글 작성
+ new.mustache에서 값을 입력받아 form태그를 통해 ArticleDTO 객체 생성
+ Entity 객체 생성
+ 리파지토리를 통해 jpa를 사용하여 db에 값저장

  *[create](https://blog.naver.com/tkgksw/222106734767)

>게시글 조회
+ index.mustache에서 게시글 제목을 클릭하면 해당 게시글의 id값이 controller로 전달
+ 전달된 id값으로 리파지토리에서 findById()를 통해 해당 id값을 가진 entity객체를 반환함
+ model 객체를 사용하여 show.mustache 로 enitity객체를 넘김


>게시글 수정
+ show.mustache에서 수정버튼클릭 하면 해당 게시글의 id값이 controller로 전달됨
+ 리파지토리에서 해당 id값을 가지는 객체가 있으면 컨트롤러로 entity객체를 반환
+ model 객체를 사용하여 entity를 update.mustache로 전달
+ 사용자가 값을 수정한후 수정 버튼 클릭
+ 새로운 dto , entity객체가 만들어짐
+ 리파지토리에서 save()가 실행될때 수정된 entity의 id가 이미 db에 존재하는 id값인 경우 기존 db에 저장된 값을 덮어씀

>게시글 삭제
+ update.mustache에서 삭제버튼 클릭
+ 게시글의 id가 url을 통해 전달됨
+ 리피지토리에서 deleteById()를 통해 해당 id를 가지는 entity 객체를 db에서 삭제



### 참고자료
코딩하면서 참고한 자료:

* [JPA 동작과정](https://gmlwjd9405.github.io/2019/08/04/what-is-jpa.html)
* [JpaAuditing을 통한 entity 생성시각 수정시각](https://blog.naver.com/tkgksw/222102167422)
* [h2-console 관련 설정](https://blog.naver.com/tkgksw/222102109020)


#### 2020 10월 3일
##### 전체흐름 view -> dto -> controller -> entity -> service -> repository -> JPA -> DB

>service의 목적
+ controller는 요청과 응답만 처리한다
+ 요청과 응답이외의 처리는 service한테 맡긴다
+ service가 repository에게 db에 접근해서 작업을 시키고 이를 수행하는 과정에서
오류가 발생하는 경우 자동으로 rollback이 되도록 하기위해 @Transactional 어노테이션을 붙였다
+ 즉 service의 각 메서드가 하나의 트랜잭션으로 묶이는 것이다

### 10월 6일
>댓글 추가
+ @MayToOne 관계
+ 여러개의 댓글이 하나의 게시글에 연결될수 있다
+ @JoinColumn(name="article_id")
+ 댓글이 어떤 게시글에 달렸는지 알수 있게 fk로 articleId를 갖고있어야 한다
+ name 속성은 외래키를 매핑할때 사용한다

>댓글목록 출력
+

### 참고자료
* [댓글과 게시글 연관관계 정리](https://blog.naver.com/tkgksw/222102392250)



