SELECT 'CREATE DATABASE login'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'login')\gexec