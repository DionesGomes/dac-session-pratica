docker build -t cliente/bd ./postgres
docker run -p 5433:5432 --name bd -d cliente/bd 

#cd app && mvn clean package && cd ..
#docker build -t cliente/app ./app
#docker run -P -p 4848:4848 -p 8686:8686 -p 8009:8009 -p 9009:9009 -p 3701:3700 -p #1099:1099 -p 1098:1098 -p 3873:3873 -p 6001:6001 -p 8099:8099 --name app -d --link #bd:host-banco cliente/app
echo 'Docker implantado.'
