#!/usr/bin/env bash


lixiaodaoaaaDirectory=/Users/lixiaodaoaaa/

for tempD in $(ls -d ${lixiaodaoaaaDirectory})
do
  # Store original working directory.
  original_wd="$(pwd)"
  cd "$tempD"
  for d2 in $(ls -d */)
  do
    pushd "$d2"
    # Do something
    popd
  done
  # Return to original working directory
  cd "${original_wd}"
done