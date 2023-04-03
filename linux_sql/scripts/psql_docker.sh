#! /bin/sh

cmd=$1
db_username=$2
db_password=$3

# Here we check the status and start docker if the service is not on
sudo systemctl status docker || systemctl start docker

# we inspect the container jrvs-sql , we use container_status=$? which will return 0 if we are able to inspect the container or some random number if not.
docker container inspect jrvs-psql
container_status=$?

#Here we use switch case to handle create|stop|start options
case $cmd in
  create)

  #if the container status = 0
  if [ $container_status -eq 0 ]; then
		echo 'Container already exists'
		exit 0
	fi

  if [ $# -ne 3 ]; then
    echo 'Create requires username and password'
    exit 1
  fi

  docker volume create pgdata
	docker run --name jrvs-psql -e POSTGRES_USERNAME=db_username -e POSTGRES_PASSWORD=db_password -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres:9.6-alpine
	exit $?
	;;
  start|stop)

  if [ $container_status -ne 0 ]; then
    echo 'Container has not been created.'
	 exit 1
	fi

	docker container $cmd jrvs-psql
	exit $?
	;;

  *)
	echo 'Illegal command'
	echo 'Commands: start|stop|create'
	exit 1
	;;
esac