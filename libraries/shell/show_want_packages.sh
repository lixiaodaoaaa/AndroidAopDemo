#!/usr/bin/env bash


wantPackages=$(adb shell pm list packages | grep want)
echo ${wantPackages}