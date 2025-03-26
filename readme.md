# ExpressoJ-Starter

<b>The project serves as a demo for ExpressoJ.</b>

## Run With Docker

<i> If you want to get started quickly: </i>

-   Ensure that Docker is installed on your system.
-   Clone the repository on your local system.
-   Execute <code>docker build -t expresso-demo:1.0.0 . </code>.
-   Now run your docker image using <code>docker run -p 5757:5757 expresso-demo:1.0.0</code>.
-   The webpage should be available at [localhost:5757](http://localhost:5757).

## Manually Set Up Environment On Your Local

-   Clone the Maven repo of ExpressoJ on your local. <i>This will help in creating ExpressoJ artifact on your local Maven repository.</i>
-   Navigate into the ExpressoJ project directory and run <code>mvn install</code>. <i>Ensure Maven is installed on your system</i>.
-   Go to <b>ExpressoJ-Starter</b> project directory and execute <code>mvn install</code> to build the ExpressoJ-Starter project.
-   The jar file will be available in the target folder. To run the application, execute the following command from root of the project: <code>java -jar target/expressoj-demo-1.0.0.jar</code>.
-   The webpage should be available at [localhost:5757](http://localhost:5757).

<picture>
    <source media="(prefers-color-scheme: dark)" srcset="./assets/logo/logo_black.png">
    <source media="(prefers-color-scheme: light)" srcset="./assets/logo/logo_light.png">
    <img alt="Logo" src="./assets/logo/logo_black.png">
</picture>
