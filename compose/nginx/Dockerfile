FROM nginx:1.21.3-alpine

# 기본 NGINX 설정 파일을 삭제
RUN rm /etc/nginx/conf.d/default.conf || true

# 사용자 정의 NGINX 설정 파일을 복사
COPY compose/nginx/nginx.conf /etc/nginx
