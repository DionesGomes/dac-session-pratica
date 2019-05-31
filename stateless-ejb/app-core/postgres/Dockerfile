FROM postgres
ENV POSTGRES_DB clientes
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD 12345

COPY create.sql /docker-entrypoint-initdb.d/
COPY insert.sql /docker-entrypoint-initdb.d/
