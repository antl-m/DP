#include "Common.h"
#include <mpi.h>

MPIGuard::MPIGuard(
    int     _Argc,
    char ** _Argv
  )
{
  MPI_Init(&_Argc, &_Argv);
}

MPIGuard::~MPIGuard()
{
  MPI_Finalize();
}
