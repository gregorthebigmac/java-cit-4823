#!/bin/bash

echo "Pushing files to big-data VM:"
rsync -var --update -P -e ssh ~/git_repos/java-cit-4823/ training@big-data:/home/training/training_materials/developer/exercises/ \
&& echo "
Pulling files from big-data VM:
" \
&& rsync -var --update -P -e ssh training@big-data:/home/training/training_materials/developer/exercises/ ~/git_repos/java-cit-4823/
