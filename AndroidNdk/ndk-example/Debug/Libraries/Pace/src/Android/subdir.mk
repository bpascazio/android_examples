################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../Libraries/Pace/src/Android/PaceJni.cpp 

OBJS += \
./Libraries/Pace/src/Android/PaceJni.o 

CPP_DEPS += \
./Libraries/Pace/src/Android/PaceJni.d 


# Each subdirectory must supply rules for building sources it contributes
Libraries/Pace/src/Android/%.o: ../Libraries/Pace/src/Android/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


