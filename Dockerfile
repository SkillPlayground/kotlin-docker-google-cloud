FROM azul/zulu-openjdk:21

LABEL org.opencontainers.image.source=https://github.com/SkillPlayground/kotlin-docker-google-cloud

WORKDIR /app

COPY app/build/install/app/ app/

EXPOSE 8080

ENTRYPOINT ["/bin/bash", "app/bin/app"]
