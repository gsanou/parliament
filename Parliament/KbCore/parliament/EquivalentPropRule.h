// Parliament is licensed under the BSD License from the Open Source
// Initiative, http://www.opensource.org/licenses/bsd-license.php
//
// Copyright (c) 2011, BBN Technologies, Inc.
// All rights reserved.

#if !defined(PARLIAMENT_EQUIVALENTPROPRULE_H_INCLUDED)
#define PARLIAMENT_EQUIVALENTPROPRULE_H_INCLUDED

#include "parliament/Platform.h"
#include "parliament/RuleEngine.h"

PARLIAMENT_NAMESPACE_BEGIN

class EquivalentPropRule : public StandardRule
{
public:
	EquivalentPropRule(KbInstance* pKB, RuleEngine* pRE);
};

PARLIAMENT_NAMESPACE_END

#endif // !PARLIAMENT_EQUIVALENTPROPRULE_H_INCLUDED
