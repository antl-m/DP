#pragma once

#include <random>

class Random
{
public:

  Random(
      const long long _Min,
      const long long _Max
    );

  long long Get();

private:

  std::random_device                       m_RandomDevice;
  std::default_random_engine               m_Engine;
  std::uniform_int_distribution<long long> m_Distribution;
};