FROM debian:stretch

RUN apt-get update && apt-get install -yq \
apache2 php7.0 \
libapache2-mod-php7.0 \
curl \
ca-certificates

RUN apt-get install -y openjdk-8-jre

COPY src/main/php/php.ini /etc/php/7.0/apache2/
COPY src/main/php/BirtEngine.inc /var/www/html/
COPY src/main/php/render.php /var/www/html/
COPY src/main/php/index.php /var/www/html/
COPY src/main/php/new_report.rptdesign /var/www/html/

COPY target/spring-boot-php-birt.jar /root/app.jar
COPY cmd.sh /usr/local/bin/

RUN chmod 400 /root/app.jar
RUN chmod +x /usr/local/bin/cmd.sh

EXPOSE 80

CMD cmd.sh
