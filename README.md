# 간단한 SNS API 서비스입니다.

```bash
├── src.main.java.com.quid.sns
│   ├── alaram
│   │   └── 댓글 작성, 좋아요시 알람 
│   │
│   ├── comment
│   │   └── 댓글 작성, 수정, 삭제
│   │
│   ├── common
│   │   └── 공통 기능 (페이징, 레디스 템플릿, 유틸)
│   │
│   ├── config
│   │   ├── JWT 토큰 설정
│   │   ├── 스프링 시큐리티 설정
│   │   └── Redis 설정
│   │
│   ├── exception
│   │   └── 커스텀 예외 처리
│   │
│   ├── kafka
│   │   └── 카프카 producer, consumer, event 등록
│   │
│   ├── like
│   │   └── 좋아요 기능
│   │
│   ├── post
│   │   ├── 게시글 작성, 수정, 삭제
│   │   └── 게시글 페이징, 캐싱
│   │
│   ├── sse
│   │   └── Server-Sent-Event 기능
│   │
│   ├── token
│   │   └── 토큰, 토큰 유틸
│   │
│   └── user
│       ├── 회원 기능
│       └── 사용자 정보 캐싱
│   
└─── Readme.md
``` 

