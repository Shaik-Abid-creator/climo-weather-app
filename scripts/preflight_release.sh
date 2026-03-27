#!/usr/bin/env bash
set -euo pipefail

if [[ -z "${JAVA_HOME:-}" ]]; then
  echo "JAVA_HOME is not set. Use JDK 17." >&2
  exit 1
fi

JAVA_VERSION_OUTPUT="$(java -version 2>&1 | head -n 1)"
echo "$JAVA_VERSION_OUTPUT"

if [[ "$JAVA_VERSION_OUTPUT" != *" 17."* ]]; then
  echo "JDK 17 is required for this project." >&2
  exit 1
fi

if [[ ! -f keystore.properties ]]; then
  echo "Missing keystore.properties (copy from keystore.properties.example)." >&2
  exit 1
fi

echo "Preflight checks passed."
