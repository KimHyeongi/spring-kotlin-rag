https://eclipse4j.tistory.com

Project Root 의 environment 내 docker-compose.yml 을 실행한다.

#> cd environment/docker-compose.yml

#> docker-compose up -d

기본 database 는 myapp 이고 하위 스키마는 myapp_rag 가 된다.

아래 pbvector 를 설치하기 위해서는 postgres 계정으로 접속후에 가능하다.

database myapp 에서 실행야 한다. (database에 설치되는 형태)

```sql
CREATE EXTENSION vector;
SELECT extversion FROM pg_extension WHERE extname = 'vector';
```


-- 이후 postgresql에 admin 계정으로 접속하여 embedding table 을 생성한다.

```sql
create table myapp_rag.myapp_vector
(
embedding_id uuid not null primary key,
text         text,
embedding    vector(1536),
metadata     json
);
```

Embedding 을 위한 요금제 TEXT 파일은 core/src/main/resources/plan-specs.txt

Client-Web 을 실행시킨후 http://localhost:8080 에 접속해서 Embedding 을 진행한 후 테스트 한다.

단, 현재 버전에서는 OpenAI의 ChatGPT 를 사용하므로 로컬에서 실행시 Environment variables 에 아래 Key 를 입력해야 한다.

LANGCHAIN4J_OPENAI_EMBEDDINGMODEL_APIKEY=xxxx;LANGCHAIN4J_OPENAI_CHATMODEL_APIKEY=xxxx

( open ai 가입후 토큰 받기 )
