#!/usr/bin/env bash
set -eo pipefail
cd ..
git submodule sync
git submodule update --init
git submodule foreach git pull origin  master
git submodule foreach git fetch origin