FROM ubuntu:14.04
MAINTAINER Ilya Epifanov <elijah.epifanov@gmail.com>

RUN apt-get update
RUN apt-get install -y wget
RUN echo "deb http://deb.smartislav.ru/oab/ /" > /etc/apt/sources.list.d/oab.smartislav.list
RUN wget http://deb.smartislav.ru/oab/pubkey.asc -O - | apt-key add -
RUN apt-get update
RUN apt-get install -y --no-install-recommends oracle-java7-jdk gcc libc6-dev-i386
RUN apt-get install -y --no-install-recommends maven
#RUN apt-get install -y --no-install-recommends openssh-server

#CMD /usr/sbin/sshd -D -o UseDNS=no -o UsePAM=no
