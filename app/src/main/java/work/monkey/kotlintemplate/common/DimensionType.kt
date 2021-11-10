package work.monkey.kotlintemplate.common

import work.monkey.kotlintemplate.R

enum class DimensionType(val dimen: Int) {
    XXSMALL(dimen = R.dimen.spacing_xxsmall),
    XSMALL(dimen = R.dimen.spacing_xsmall),
    XTSMALL(dimen = R.dimen.spacing_xtsmall),
    SMALL(dimen = R.dimen.spacing_small),
    MEDIUM_SMALL(dimen = R.dimen.spacing_medium_small),
    MEDIUM(dimen = R.dimen.spacing_medium),
    MEDIUM_LARGE(dimen = R.dimen.spacing_medium_large),
    LARGE(dimen = R.dimen.spacing_large),
    XLARGE(dimen = R.dimen.spacing_xlarge),
    XXLARGE(dimen = R.dimen.spacing_xxlarge),
    XXXLARGE(dimen = R.dimen.spacing_xxxlarge)
}