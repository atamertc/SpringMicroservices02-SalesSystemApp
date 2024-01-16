#ilk olarak kullanılacak JDK sürümü seçilir.
FROM amazoncorretto:19
#--------------------------------------------
# Jar dosyasının docker'a kopyalanması:
#1.yol:
#COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar

#2.yol: Mikroservislerde her bir jar file'i otomatik bulup yukler
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
#--------------------------------------------
# Eğer sistemde güncellemelerin yapılmasını istersen.
CMD apt-get update -y
#--------------------------------------------
# Jarı çalıştırma komutu:
ENTRYPOINT ["java","-jar","/app.jar"]
#--------------------------------------------

#============================= git-config-server ================================
#docker build --build-arg JAR_FILE=git-config-server/build/libs/git-config-server-v.0.0.1.jar -t atamertc/ssa_configserver:v001 .

#============================= auth-service ================================
#docker build --build-arg JAR_FILE=auth-service/build/libs/auth-service-v.0.0.1.jar -t atamertc/ssa_authservice:v001 .

#============================= user-service ================================
#docker build --build-arg JAR_FILE=user-service/build/libs/user-service-v.0.0.1.jar -t atamertc/ssa_userservice:v001 .

#============================= api-gateway-service ================================
#docker build --build-arg JAR_FILE=api-gateway-service/build/libs/api-gateway-service-v.0.0.1.jar -t atamertc/ssa_apigateway:v001 .
