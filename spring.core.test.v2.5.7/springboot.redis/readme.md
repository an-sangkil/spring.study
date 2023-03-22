# Redis Pub-sub Example
- 레디스를 이용한 간단한 Queue 프로그래밍

# 테스트 URL
- GET    : /room/find-all : 유효한 채널(토픽) 정보 반환 
- PUT    : /room/{roomId} : 채널(토픽)을 생성하여 Listener에 등록한뒤 방아이디를 찾을 Topic Channel 을 (Map 데이터 Key,Value로)에 저장 한다.
- POST   : /room/send-message/{roomId} : 대화를 나눌 방에 메세지를 전송한다. 
- DELETE : /room/{roomId} : 채팅방을 삭제한다. Redis 의 토픽과, Map에 저장된 정보 삭제
(RDB를 사용하지 않았기 때문에 채팅방에 대한 정보를 Map에 저장하여 사용함)

# 프로세스

