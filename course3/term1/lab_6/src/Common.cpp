#include "Common.h"


Random::Random(
    const NumT _Min,
    const NumT _Max
  ) :
    m_Engine(std::random_device()()),
    m_Distribution(_Min, _Max)
{
  // Empty
}

NumT Random::Get()
{
  return m_Distribution(m_Engine);
}
