# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.10

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /home/richard/Desktop/clion-2018.1.1/bin/cmake/bin/cmake

# The command to remove a file.
RM = /home/richard/Desktop/clion-2018.1.1/bin/cmake/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/richard/Desktop/NettyDemo

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/richard/Desktop/NettyDemo/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/NettyDemo.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/NettyDemo.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/NettyDemo.dir/flags.make

CMakeFiles/NettyDemo.dir/src/domain-socket-c/FloatingBarDemo.c.o: CMakeFiles/NettyDemo.dir/flags.make
CMakeFiles/NettyDemo.dir/src/domain-socket-c/FloatingBarDemo.c.o: ../src/domain-socket-c/FloatingBarDemo.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/richard/Desktop/NettyDemo/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object CMakeFiles/NettyDemo.dir/src/domain-socket-c/FloatingBarDemo.c.o"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/NettyDemo.dir/src/domain-socket-c/FloatingBarDemo.c.o   -c /home/richard/Desktop/NettyDemo/src/domain-socket-c/FloatingBarDemo.c

CMakeFiles/NettyDemo.dir/src/domain-socket-c/FloatingBarDemo.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/NettyDemo.dir/src/domain-socket-c/FloatingBarDemo.c.i"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /home/richard/Desktop/NettyDemo/src/domain-socket-c/FloatingBarDemo.c > CMakeFiles/NettyDemo.dir/src/domain-socket-c/FloatingBarDemo.c.i

CMakeFiles/NettyDemo.dir/src/domain-socket-c/FloatingBarDemo.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/NettyDemo.dir/src/domain-socket-c/FloatingBarDemo.c.s"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /home/richard/Desktop/NettyDemo/src/domain-socket-c/FloatingBarDemo.c -o CMakeFiles/NettyDemo.dir/src/domain-socket-c/FloatingBarDemo.c.s

CMakeFiles/NettyDemo.dir/src/domain-socket-c/FloatingBarDemo.c.o.requires:

.PHONY : CMakeFiles/NettyDemo.dir/src/domain-socket-c/FloatingBarDemo.c.o.requires

CMakeFiles/NettyDemo.dir/src/domain-socket-c/FloatingBarDemo.c.o.provides: CMakeFiles/NettyDemo.dir/src/domain-socket-c/FloatingBarDemo.c.o.requires
	$(MAKE) -f CMakeFiles/NettyDemo.dir/build.make CMakeFiles/NettyDemo.dir/src/domain-socket-c/FloatingBarDemo.c.o.provides.build
.PHONY : CMakeFiles/NettyDemo.dir/src/domain-socket-c/FloatingBarDemo.c.o.provides

CMakeFiles/NettyDemo.dir/src/domain-socket-c/FloatingBarDemo.c.o.provides.build: CMakeFiles/NettyDemo.dir/src/domain-socket-c/FloatingBarDemo.c.o


# Object files for target NettyDemo
NettyDemo_OBJECTS = \
"CMakeFiles/NettyDemo.dir/src/domain-socket-c/FloatingBarDemo.c.o"

# External object files for target NettyDemo
NettyDemo_EXTERNAL_OBJECTS =

NettyDemo: CMakeFiles/NettyDemo.dir/src/domain-socket-c/FloatingBarDemo.c.o
NettyDemo: CMakeFiles/NettyDemo.dir/build.make
NettyDemo: CMakeFiles/NettyDemo.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/richard/Desktop/NettyDemo/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking C executable NettyDemo"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/NettyDemo.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/NettyDemo.dir/build: NettyDemo

.PHONY : CMakeFiles/NettyDemo.dir/build

CMakeFiles/NettyDemo.dir/requires: CMakeFiles/NettyDemo.dir/src/domain-socket-c/FloatingBarDemo.c.o.requires

.PHONY : CMakeFiles/NettyDemo.dir/requires

CMakeFiles/NettyDemo.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/NettyDemo.dir/cmake_clean.cmake
.PHONY : CMakeFiles/NettyDemo.dir/clean

CMakeFiles/NettyDemo.dir/depend:
	cd /home/richard/Desktop/NettyDemo/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/richard/Desktop/NettyDemo /home/richard/Desktop/NettyDemo /home/richard/Desktop/NettyDemo/cmake-build-debug /home/richard/Desktop/NettyDemo/cmake-build-debug /home/richard/Desktop/NettyDemo/cmake-build-debug/CMakeFiles/NettyDemo.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/NettyDemo.dir/depend

