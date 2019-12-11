#!/bin/bash

if [ -z $JAVA_HOME ];then
    export JAVA_HOME=/usr/local/jdk1.8.0_121
fi
if [ -z $M2_HOME ];then
    export M2_HOME=/usr/local/maven3.5
fi
export PATH=$JAVA_HOME/bin:$PATH
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar

dir=`dirname $0`
export dir=`cd $dir; pwd`

export time=`date +"%Y%m%d%H%M%S"`

PROFILE="test"



env="$1"
echo "env: $env"

while [ $# -gt 0 ]; do
    case "$env" in
        --prod)
            PROFILE="prod"
            shift
            ;;
        --test)
            PROFILE="test"
            shift
            ;;
        --dev)
            PROFILE="dev"
            shift
            ;;
        --local)
            PROFILE="local"
            shift
            ;;
        *)
            break
            ;;
    esac
done

echo "PROFILE= $PROFILE"

function compile() {
    cd $dir
    $M2_HOME/bin/mvn -U clean install -DskipTests -P$PROFILE
    if [ $? -ne 0 ]; then
        echo "Compile error!" 1>&2
        exit 1
    fi
}


function main() {
    compile
    echo "Done!"
}

main

exit 0
