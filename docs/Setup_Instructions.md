# Setup Instructions

## JDK Version Used

This project is developed and tested using Java 17 LTS. Java 17 provides a stable long-term support release that is well suited for learning core Java concepts and building console applications.

## Install and Configure Java (JDK)

1. Download and install a Java 17 JDK from a trusted vendor, such as Oracle JDK, OpenJDK, or Adoptium.
2. Set the `JAVA_HOME` environment variable to the installation directory of the JDK.
3. Add the JDK `bin` directory to your system `PATH` so `javac` and `java` are available from the terminal.

### Example configuration on macOS or Linux

```bash
export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home"
export PATH="$JAVA_HOME/bin:$PATH"
```

### Example configuration on Windows (PowerShell)

```powershell
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-17'
$env:Path = "$env:JAVA_HOME\bin;${env:Path}"
```

## Verify Installation

Run the following command:

```bash
java -version
```

You should see output that includes a Java 17 version string, for example:

```text
java version "17.0.x" 2023-... 
Java(TM) SE Runtime Environment (build 17.0.x+...)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.x+..., mixed mode)
```

## Run a Hello World Program

1. Create a file named `HelloWorld.java` with the following content:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

2. Compile the program:

```bash
javac HelloWorld.java
```

3. Run the compiled class:

```bash
java HelloWorld
```

4. Expected output:

```text
Hello, World!
```

This confirms that the JDK is installed and working properly.

## Clone and Run the Project

```bash
git clone https://github.com/gauravkr-io/LearnTrack-Student-and-Course-Management-System.git
cd LearnTrack-Student-and-Course-Management-System
javac -cp src/main/java -d out src/main/java/com/airtribe/learntrack/Main.java
java -cp out com.airtribe.learntrack.Main
```

## JDK vs JRE vs JVM

- JVM: The Java Virtual Machine executes compiled Java bytecode and provides the runtime environment for Java applications.
- JRE: The Java Runtime Environment includes the JVM and the standard Java libraries required to run Java programs.
- JDK: The Java Development Kit includes the JRE, JVM, and developer tools such as the compiler (`javac`) needed to build and package Java applications.
