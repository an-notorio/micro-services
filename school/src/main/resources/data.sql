SELECT 'CREATE DATABASE schools'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'schools')\gexec