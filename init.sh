#!/bin/sh

# Variáveis de Ambiente (ajuste conforme necessário)
DB_HOST=${DB_HOST:-sqlserver-service.sqlserver.svc.cluster.local}
DB_PORT=${DB_PORT:-1433}

# Esperar até que o banco de dados esteja disponível
echo "Esperando o SQL Server ficar disponível na porta ${DB_PORT}..."
until nc -z -v -w30 $DB_HOST $DB_PORT
do
  echo "Aguardando a conexão com o banco de dados..."
  sleep 5
done

echo "Conexão com o banco de dados estabelecida."

# Iniciar a aplicação
echo "Iniciando a aplicação..."
java $JAVA_OPTS -jar /app/app.jar
