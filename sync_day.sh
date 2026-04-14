#!/bin/bash

set -e

# --- Input validation ---
if [ -z "$1" ]; then
  echo "Usage: $0 <day_number>"
  exit 1
fi

DAY=$1
PREV=$((DAY - 1))

if [ "$DAY" -lt 1 ]; then
  echo "Day must be >= 1"
  exit 1
fi

# --- Ensure we're on main ---
git checkout main
git pull origin main

# --- Pull today's folder ---
echo "Pulling Day${DAY} from teacher branch..."
git checkout teacher -- "Day${DAY}/"
git rm -r --ignore-unmatch "Day${DAY}/Solutions"

# --- Pull previous day's Solutions ---
if [ "$PREV" -ge 1 ]; then
  echo "Pulling solutions from Day${PREV}..."
  git checkout teacher -- "Day${PREV}/Solutions/"
else
  echo "No previous day solutions (Day 0 skipped)."
fi

# --- Commit changes ---
git add .
git commit -m "Day${DAY}"

echo "Done: Day${DAY} synced and committed."