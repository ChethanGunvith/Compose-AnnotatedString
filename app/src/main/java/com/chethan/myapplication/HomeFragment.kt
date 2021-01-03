package com.chethan.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.gesture.tapGestureFilter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

interface ClickListener {
    fun onClickOfTermsAndCondition()
    fun onClickOfRequiredDocuments()
    fun onClickOfIndustryTerms()
    fun onClickOfPrivacyPolicy()
}

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                SetUserTermsAndPrivacyText(object : ClickListener {

                    override fun onClickOfTermsAndCondition() {
                        findNavController().navigate(HomeFragmentDirections.showTermsConditionFragment())
                    }

                    override fun onClickOfRequiredDocuments() {
                        findNavController().navigate(HomeFragmentDirections.showTermsConditionFragment())
                    }

                    override fun onClickOfIndustryTerms() {
                        findNavController().navigate(HomeFragmentDirections.showTermsConditionFragment())
                    }

                    override fun onClickOfPrivacyPolicy() {
                        findNavController().navigate(HomeFragmentDirections.showPrivacyFragment())
                    }
                })
            }
        }

    }


}

@Composable
fun SetUserTermsAndPrivacyText(
    listener: ClickListener
) {

    Column(modifier = Modifier.padding(15.dp)) {

        val layoutResult = remember {
            mutableStateOf<TextLayoutResult?>(null)
        }

        val agreementText = stringResource(R.string.Info_Agree)
        val termsCondition = stringResource(R.string.Info_Terms_Conditions)
        val requiredDocuments =
            stringResource(R.string.Info_Docs)
        val industryTerms =
            stringResource(R.string.Info_Industry_Terms)
        val privacyPolicy = stringResource(R.string.Info_Privacy_Policy)

        val agreementReplaceText =
            agreementText.replace("{terms_and_conditions}", termsCondition)
                .replace("{required_documents}", requiredDocuments)
                .replace("{industry_terms}", industryTerms)
                .replace("{privacy_policy}", privacyPolicy)

        val indexOfTerms = agreementReplaceText.indexOf(termsCondition)
        val indexOfDocument = agreementReplaceText.indexOf(requiredDocuments)
        val indexOfIndustryTerms = agreementReplaceText.indexOf(industryTerms)
        val indexOfPrivacy = agreementReplaceText.indexOf(privacyPolicy)

        val annotatedString = with(AnnotatedString.Builder(agreementReplaceText)) {

            addStyle(
                SpanStyle(color = Color.Black, textDecoration = TextDecoration.Underline),
                indexOfTerms,
                indexOfTerms + termsCondition.length
            )

            addStringAnnotation(
                tag = "termsCondition",
                annotation = termsCondition,
                start = indexOfTerms,
                end = indexOfTerms + termsCondition.length
            )

            addStyle(
                SpanStyle(color = Color.Black, textDecoration = TextDecoration.Underline),
                indexOfDocument,
                indexOfDocument + requiredDocuments.length
            )

            addStringAnnotation(
                tag = "requiredDocuments",
                annotation = requiredDocuments,
                start = indexOfDocument,
                end = indexOfDocument + requiredDocuments.length
            )

            addStyle(
                SpanStyle(color = Color.Black, textDecoration = TextDecoration.Underline),
                indexOfIndustryTerms,
                indexOfIndustryTerms + industryTerms.length
            )

            addStringAnnotation(
                tag = "IndustryTerms",
                annotation = industryTerms,
                start = indexOfIndustryTerms,
                end = indexOfIndustryTerms + industryTerms.length
            )

            addStyle(
                SpanStyle(color = Color.Black, textDecoration = TextDecoration.Underline),
                indexOfPrivacy,
                indexOfPrivacy + privacyPolicy.length
            )

            addStringAnnotation(
                tag = "privacyPolicy",
                annotation = privacyPolicy,
                start = indexOfPrivacy,
                end = indexOfPrivacy + privacyPolicy.length
            )

            toAnnotatedString()
        }

        Text(
            text = annotatedString,
            style = TextStyle(
                color = Color(0xFF333333),
                fontSize = 14.sp,
                textAlign = TextAlign.Start
            ),
            modifier = Modifier.padding(start = 10.dp, top = 24.dp)
                .tapGestureFilter { offsetPosition ->
                    layoutResult.value?.let {
                        val position = it.getOffsetForPosition(offsetPosition)

                        annotatedString.getStringAnnotations(position, position).firstOrNull()
                            ?.let { result ->
                                when (result.tag) {
                                    "termsCondition" -> {
                                        listener.onClickOfTermsAndCondition()
                                    }

                                    "requiredDocuments" -> {
                                        listener.onClickOfRequiredDocuments()
                                    }

                                    "IndustryTerms" -> {
                                        listener.onClickOfIndustryTerms()
                                    }

                                    "privacyPolicy" -> {
                                        listener.onClickOfPrivacyPolicy()
                                    }
                                }
                            }
                    }
                },
            onTextLayout = { layoutResult.value = it }
        )

        Spacer(modifier = Modifier.padding(top = 40.dp))
    }
}









