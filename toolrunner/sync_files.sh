#!/bin/bash

rsync -vr -P -e ssh ~/git_repos/java-cit-4823/toolrunner/src/ training@big-data:/home/training/training_materials/developer/exercises/toolrunner/src/
