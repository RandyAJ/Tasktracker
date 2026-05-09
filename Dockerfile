FROM ubuntu:latest
LABEL authors="rasota"

ENTRYPOINT ["top", "-b"]