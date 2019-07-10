//how many devices available in total
#define MAX_GPU 4 

typedef struct{
	int device; //gpu device id @server; 0 or 1 in our case
	char server[255]; //server; garuda-0 or garuda-1
	char path[255]; //where the executable file locates
}GPUdeviceInfo;

//user name to login to remote server
static char *USER="liubing";

static GPUdeviceInfo gpudeviceinfo[MAX_GPU]={
	//master gpu
	{0, "garuda-1.ddns.comp.nus.edu.sg", "."},
	//local slave gpus
	{1, "garuda-1.ddns.comp.nus.edu.sg", "."},
	//remote slave gpus
	{0, "garuda-0.ddns.comp.nus.edu.sg", "~"},
	{1, "garuda-0.ddns.comp.nus.edu.sg", "~"}
};

