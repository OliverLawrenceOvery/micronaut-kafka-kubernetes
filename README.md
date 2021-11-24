# Micronaut-Kafka-Kubernetes Application
Micronaut application that uses:

1) MongoDB as data storage that is deployed on Kubernetes.
2) Kafka as a messaging service
3) A Fluentbit-Elasticsearch-Grafana stack for visualising application logs

### Pre-requisites

### MongoDB

Numerous resources are created through the deployment of MongoDB on Kubernetes:

1) Secret (mongodb-secrets.yaml)
   1) Used to mount our desired username and password to the containers
   2) Kubernetes will store the secret in a base 64 encoded format. To encode your desired username/password into base 64 format, execute the following:
    ```
    echo "username" | base64
    ```
2) Persistent Volume (mongodb-pv.yaml)
   1) Used to store persistent data
   2) Is an object which maps to a storage location
3) Persistent Volume Claim (mongodb-pvc.yaml)
   1) Is an object that requests storage
4) Deployment (mongodb-deployment.yaml)
   1) Uses the official mongo image from docker hub
    ```
     containers:
      - image: mongo
        name: mongo
    ```
   2) Environment variables injected through secrets
    ```
     env:
     - name: MONGO_INITDB_ROOT_USERNAME
       valueFrom:
         secretKeyRef:
           name: mongo-creds
           key: username
     - name: MONGO_INITDB_ROOT_PASSWORD
       valueFrom:
         secretKeyRef:
           name: mongo-creds
           key: password
     ```
   3) Liveness and Readiness probes ensure the container does not get stuck in a loop upon start-up
     ```
     livenessProbe:
          exec:
            command:
              - mongo
              - --disableImplicitSessions
              - --eval
              - "db.adminCommand('ping')"
          initialDelaySeconds: 30
          periodSeconds: 10
          timeoutSeconds: 5
          successThreshold: 1
          failureThreshold: 6
     readinessProbe:
          exec:
            command:
              - mongo
              - --disableImplicitSessions
              - --eval
              - "db.adminCommand('ping')"
          initialDelaySeconds: 30
          periodSeconds: 10
          timeoutSeconds: 5
          successThreshold: 1
          failureThreshold: 6
     ```
5) Mongo Client Deployment (mongodb-client.yaml)
   1) Use to access MongoDB database
   2) To use:
      1) Exec into client
      ```
      kubectl exec deployment/mongo-client -it -- /bin/bash
      ```
      2) Login into the MongoDB shell
      ```
      mongo --host mongo-nodeport-svc --port 27017 -u adminuser -p password123
      ```
      3) Display list of DBs
      ```
      show dbs
      ```
      4) Get inside a particular DB
      ```
      use <db_name>
      ```
      5) Display a list of collections inside the DB
      ```
      show collections
      ```
      6) List data inside a DB collection
      ```
      db.<collection_name>.find()
      ```
6) 
      