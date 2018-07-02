#!/bin/bash

java \
    -server -Xmx128m -Xms128m \
    -XX:MaxMetaspaceSize=64m -XX:MaxMetaspaceExpansion=0 \
    -Djava.net.preferIPv4Stack=true \
    -Djava.rmi.server.hostname=0.0.0.0 \
    -Dcom.sun.management.jmxremote.local.only=false \
    -Dcom.sun.management.jmxremote=true \
    -Dcom.sun.management.jmxremote.host=0.0.0.0 \
    -Dcom.sun.management.jmxremote.port=9000 \
    -Dcom.sun.management.jmxremote.rmi.port=9000 \
    -Dcom.sun.management.jmxremote.ssl=false \
    -Dcom.sun.management.jmxremote.authenticate=false \
    -Duser.language=pt -Duser.country=BR -Duser.timezone=America/Sao_Paulo \
    -jar /root/birt/app.jar >> /var/log/birt.log &

source /etc/apache2/envvars
exec apache2 -DFOREGROUND