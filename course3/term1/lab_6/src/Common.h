#pragma once

#include <vector>
#include <random>

inline constexpr int INVALID = -1;

inline constexpr int DEFAULT_H = 3;
inline constexpr int DEFAULT_W = 15;

class MPIGuard
{
public:
  MPIGuard(
      int     _Argc,
      char** _Argv
    );

  ~MPIGuard();
};
