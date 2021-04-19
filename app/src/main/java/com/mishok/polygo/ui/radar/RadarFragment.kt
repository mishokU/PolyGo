package com.mishok.polygo.ui.radar

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import com.mishok.polygo.R
import com.mishok.polygo.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_radar.*
import javax.inject.Inject


class RadarFragment : BaseFragment<RadarState, RadarViewModel>() {

    @Inject
    override lateinit var viewModel: RadarViewModel

    //Do not need it there
    override val layoutRes: Int = R.layout.fragment_radar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        startAnimation()
    }

    private fun startAnimation() {
        // Create an animation instance
        val an: Animation = RotateAnimation(
            0.0f,
            360.0f,
            (backgroundImage.measuredWidth / 4).toFloat(),
            (backgroundImage.measuredHeight / 4).toFloat()
        )

        // Set the animation's parameters
        // Set the animation's parameters
        an.duration = 10000 // duration in ms
        an.repeatCount = -1 // -1 = infinite repeated
        an.repeatMode = Animation.REVERSE // reverses each repeat
        an.fillAfter = true // keep rotation after animation

        // Apply animation to image view
        // Apply animation to image view
        backgroundImage.animation = an
    }

    private fun initViews() {
        openARButton.setOnClickListener {
            viewModel.requestPermission(requireContext())
        }
    }

    override fun onStateChange(state: RadarState) {
        if (state.isCamera) {
            viewModel.openAR()
        }
    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        return ComposeView(requireContext()).apply {
//            setContent {
//                CreateFragmentBackground()
//                Box(
//                    contentAlignment = Alignment.BottomCenter,
//                    modifier = Modifier.padding(20.dp)
//                ) {
//                    CreateStartRadarButton()
//                }
//            }
//        }
//    }
//
//    @Preview
//    @Composable
//    fun CreateStartRadarButton() {
//        Button(
//            onClick = {
//
//            },
//            modifier = Modifier.width(170.dp).height(60.dp),
//            border = BorderStroke(1.dp, Color.Green),
//            shape = RoundedCornerShape(30.dp), // = 50% percent
//            colors = ButtonDefaults.buttonColors(
//                backgroundColor = Color.White
//            )
//        ) {
//            Text(
//                text = "ВКЛЮЧИТЬ",
//                fontSize = 16.sp,
//                fontFamily = FiraSansFamily,
//                fontStyle = FontStyle.Normal,
//                fontWeight = FontWeight.Bold,
//                color = colorResource(id = R.color.black)
//            )
//        }
//    }
//
//    private val FiraSansFamily = FontFamily(
//        Font(R.font.pt_sans_bold, FontWeight.Bold),
//    )
//
//    @Preview
//    @Composable
//    fun CreateFragmentBackground() {
//        Image(
//            painter = painterResource(R.drawable.radar_background),
//            modifier = Modifier
//                .fillMaxHeight()
//                .fillMaxWidth(),
//            contentScale = ContentScale.Crop,
//            contentDescription = "Animated radar background image"
//        )
//    }
}