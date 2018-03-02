Stellar-ERBaseline
====================
|Branch|Build|Coverage|
|:-----|:----:|:----:|
|*master*|[![Build Status](https://travis-ci.org/data61/stellar-ERBaseline.svg?branch=master)](https://travis-ci.org/data61/stellar-ERBaseline)|[![Coverage Status](https://coveralls.io/repos/github/data61/stellar-ERBaseline/badge.svg?branch=master)](https://coveralls.io/github/data61/stellar-ERBaseline?branch=master)|
|*devel*|[![Build Status](https://travis-ci.org/data61/stellar-ERBaseline.svg?branch=devel)](https://travis-ci.org/data61/stellar-ERBaseline)|[![Coverage Status](https://coveralls.io/repos/github/data61/stellar-ERBaseline/badge.svg?branch=devel)](https://coveralls.io/github/data61/stellar-ERBaseline?branch=devel)|

*Original source code sourced from SERF framework http://infolab.stanford.edu/serf/*

## Docker Image
https://hub.docker.com/r/wangzhen263/stellar-erbaseline


## Quickstart

1.To re-compile this package:

```bash
mvn package
```
2.You can check the `help` command to understand parameters

```bash
java -jar "target/Baseline-1.3.3-jar-with-dependencies.jar" --help

Usage: Stellar ER Baseline(1.3.3) [options]
  Options:
    --cli, -c
      To run in cmd mode, use -c [dataset]
    --help, -h

    --rest, -r
      To start rest server mode
      Default: false
```
3.To run ER in command line mode

```bash
java -jar "target/Baseline-1.3.3-jar-with-dependencies.jar" -c datasets/ACM_DBLP/param.json
```
4.To run ER in REST-HTTP mode

```bash
java -jar "target/Baseline-1.3.3-jar-with-dependencies.jar" -r
```

References
-----------------------------------------------------------------------

[1] Swoosh: A Generic Approach to Entity Resolution
    Omar Benjelloun, Hector Garcia-Molina, Jeff Jonas, Qi Su, Jennifer
    Widom. Stanford University Technical Report, 2005.
    Available at: http://dbpubs.stanford.edu:8090/pub/2005-5
