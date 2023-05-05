spring:
  datasource:
    url: jdbc:h2:mem:bankdb
    username: sa
    password: password
    driver-class-name: org.h2.Driver
  jpa:
    show-sql: true
    defer-datasource-initialization: true
    hibernate:
      ddl-auto: create
    properties:
      hibernate:
        dialect: org.hibernate.dialect.H2Dialect
  sql:
    init:
      mode: always

logging:
  level:
    root: INFO
    org:
      springframework: INFO
