SELECT 'CREATE DATABASE students'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'students')\gexec