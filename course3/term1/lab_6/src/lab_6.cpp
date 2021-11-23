#include <iostream>
#include <array>
#include "mpi.h"


class MPIGuard
{
public:
  MPIGuard(
      int     _Argc,
      char ** _Argv
    )
  {
    MPI_Init(&_Argc, &_Argv);
  }

  ~MPIGuard()
  {
    MPI_Finalize();
  }
};

class AlgBase
{
public:

  AlgBase()
  {

  }

protected:

  const int m_CurrentProcess;
  const int m_ProcessesCount;

  static Matrix m_MatrixA

};

class SequencedAlg
{
public:

  SequencedAlg()
  {

  }

private:

  const int CurrentProcess;
  const int ProcessesCount;
};

int main(int argc, char ** argv )
{
  MPIGuard Guard(argc, argv);
  return 0;
}