---
type: development
lang: en
permalink: /development/
---

# Development

{% include navigation.md %}

## Getting started

Install [Docker](https://www.docker.com/) and open a Unix Shell or Docker Quickstart Terminal.

```bash
# start a pull-request and change into the docker folder
cd docker

# Change the constant PROFILE in the class KanboardConstant to PROFILE_DEVELOPMENT
find ../project -name 'KanboardConstant.java' -exec sed -i 's|PROFILE = PROFILE_BUILD|PROFILE = PROFILE_DEVELOPMENT|' {}  ";"

# start development container
./containerStart.sh
```

## Only for Windows users

You will probably run into a bug, that you can't execute the .sh files. Please make sure to use the **dos2unix** command once to change the .sh files into the unix format.

```bash
cd docker
find . -name '*.sh' -type f -exec dos2unix {} ";"
```
