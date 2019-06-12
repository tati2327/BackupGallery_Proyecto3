cmake_minimum_required(VERSION 3.13)
project(Sockets)

set(CMAKE_CXX_STANDARD 14)

add_executable(Sockets main.cpp Client.cpp Client.h)