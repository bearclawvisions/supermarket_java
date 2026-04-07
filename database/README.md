# Database initialization
Run `podman compose --env-file ../.env.docker up -d` in this directory.  
This command will start the database container using the configuration specified in the `docker-compose.yml` file and the environment variables defined in `.env.docker`.  
The database container will be accessible at `localhost:5432` with the credentials specified in `.env.docker`.  

Download DBeaver community edition from a package manager. For Bazzite download the GUI with the Bazaar plugin.  
Create a new connection to the database and use the credentials specified in `.env.docker`.  

All the variables are defined in the `.env.docker` file.  
The `.env.docker` file contains the following variables:
- `POSTGRES_USER`: The username for the database.
- `POSTGRES_PASSWORD`: The password for the database.
- `POSTGRES_DB`: The name of the database.
- `POSTGRES_HOST`: The hostname of the database.
- `POSTGRES_PORT`: The port number of the database.

# Migrations
Migrations are manual and defined in the `migrations` directory. Of the `domain` module under resources.  
This could be automated (Flyway) in the future and/or transferred to the `persistence` module.  