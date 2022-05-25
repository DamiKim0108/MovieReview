# aop-part3-chapter04 -  도서 리뷰 앱

보다 자세한 내용을 저의 [블로그 포스터](https://whyprogrammer.tistory.com/591)에서 확인할 수 있습니다.

# 목차

1. 인트로 (완성앱 & 구현 기능 소개)
2. 인터파크 도서 Open API 신청하기
3. 도서 리스트 화면 - Open API를 통해 도서 목록 가져오기
4. 도서 리스트 화면 - RecyclerView 활용하여 아이템 그려보기
5. 도서 리스트 화면 - 도서 목록 보여주기
6. 도서 검색 페이지 - 도서 검색하기
7. 도서 검색 페이지 - 검색 기록 저장하기
8. 도서 상세 페이지 - 도서 상세 보여주기
9. 어떤 것을 추가로 개발할 수 있을까?
10. 아웃트로



# 결과화면

![1](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbKXDk5%2Fbtq6Sk0vYcX%2FEwI21ldFeKDPN9r410Dl6k%2Fimg.png)



![2](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FdmN3gO%2Fbtq6RNhmfW0%2FsWb10sKlAdXlIrhO4YxNnk%2Fimg.png)



# 이 챕터를 통해 배우는 것

- **RecyclerView** 사용하기
- **View Binding** 사용하기
- **Retrofit** 사용하기 (API 호출)
- **Glide** 사용하기 (이미지 로딩)
- **Android Room** 사용하기 (복습 파트2, 챕터4 계산기, Local DB)
- Open API 사용해보기



### 도서 리뷰 앱

인터파크 Open API 를 통해 베스트셀러 정보를 가져와서 화면에 그릴 수 있음.

인터파크 Open API 를 통해 검색어에 해당하는 책 목록을 가져와서 화면에 그릴 수 있음.

Local DB 를 이용하여 검색 기록을 저장하고 삭제할 수 있음.

Local DB 를 이용하여 개인 리뷰를 저장할 수 있음.

# open API

네이버 책은 항목에 베스트 셀러가 없어서 인터파크 사용하였음

공공 데이터 포털에서도 오픈API를 제공하고 있음.

인터파크 인증키 (B37540CD55F6A52183F9AA6EACBC2767918F2070D1A3015C87F24AADD094279E) (삭제 예정)

http://book.interpark.com/blog/bookpinion/bookpinionOpenAPIInfo.rdo

포스트맨을 사용하여 api 테스트 진행

레트로핏 사용(https://square.github.io/retrofit/)함

gson tkdyd

# 리사이클러 뷰

재활용 (근처의 데이터만 다시 할당)

# 뷰 바인딩 사용

레이아웃 파일과 유사한 이름으로 코드에서 레이아웃 파일 속성에 접근이 용이하도록
