#include "simulation.cu"
#include "config_multigpu.h"

/******************************************************/
#define MASTER 1
#define SLAVE 2

#include <pthread.h>

typedef struct{
	int _MSstatus;
	int _id;
	char _cmd[255];
	char _filecmd[255];
}ThreadData;

typedef struct{
	int _id;
	int _traces;
	unsigned int* _data_h;
	unsigned int* _data_d;
}ThreadData_master;

static int useMultiGPU=0,MSstatus=0;
static int _step,_count,_jump;

void* launchGPU_slave(void *p){
	ThreadData *thd = (ThreadData*)p;
	system(thd->_cmd);
	if(strstr(thd->_cmd,"ssh")!=NULL) system(thd->_filecmd);

	pthread_exit(NULL);
}

void* launchGPU_master(void* p){
	ThreadData_master *thd = (ThreadData_master*)p;
	struct cudaDeviceProp props;
	cudaGetDeviceProperties(&props, thd->_id);
	cudaMalloc((void **)&(thd->_data_d), sizeof(unsigned int) * (_count + 1 )* _jump);
	cudaMemset(thd->_data_d, 0, sizeof(unsigned int) * (_count + 1 )* _jump);
	cudaThreadSynchronize();
	cudaEvent_t start, stop;
	cudaEventCreate(&start);
	cudaEventCreate(&stop);
	cudaEventRecord(start, 0);

	simulation(&props, thd->_data_d, thd->_traces, thd->_id);

	cudaEventRecord(stop, 0);
	cudaEventSynchronize(stop);
	printf("Status: %s\n", cudaGetErrorString(cudaGetLastError()));
	cudaMemcpy(thd->_data_h, (unsigned int*)(thd->_data_d) + _jump, sizeof(unsigned int) * _count * _jump, cudaMemcpyDeviceToHost);
	cudaThreadSynchronize();
	float elapsedTime;
	cudaEventElapsedTime(&elapsedTime, start, stop);
	printf("Time: %.2fs\n", (double)elapsedTime/1000);

	cudaEventDestroy(start);
	cudaEventDestroy(stop);
	cudaFree(thd->_data_d);
	
	pthread_exit(NULL);
}


