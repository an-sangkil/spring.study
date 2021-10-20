#FROM cassandra:latest
FROM cassandra:3.11
RUN mkdir /data
COPY data/initial-schema.cql.sql /data/

RUN sed -i 's/^authenticator.\+$/authenticator: PasswordAuthenticator/g' /etc/cassandra/cassandra.yaml
# RUN sed -ri -e 's/^(authenticator:).*/\1 PasswordAuthenticator/' -e 's/^(authorizer:).*/\1 CassandraAuthorizer/' /etc/cassandra/cassandra.yaml
