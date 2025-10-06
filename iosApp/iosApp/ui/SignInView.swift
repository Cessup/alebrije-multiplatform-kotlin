//
//  SignInView.swift
//  iosApp
//
//  Created by Cess UP on 28/09/25.
//

import Shared
import SwiftUI

struct SignInView: View {
	@State private var email = ""
	@State private var keyword = ""
	@StateObject private var viewModel = SignInViewModelWrapper()
	@State private var navigateToHome = false
	
	
	var body: some View {
		VStack {
			Spacer()
			
			VStack(spacing: 16) {
				Text("Sign In")
					.font(.title)
					.fontWeight(.bold)
				
				TextField("Email", text: $email)
					.textFieldStyle(.roundedBorder)
					.keyboardType(.emailAddress)
				
				SecureField("Password", text: $keyword)
					.textFieldStyle(.roundedBorder)
				
				if let errorState = viewModel.currentState as? UiStateNetworkError {
					Text(errorState.message)
						.foregroundColor(.red)
				}
				
				if viewModel.currentState is UiStateLoading {
					ProgressView()
				}
				
				Button("Sign In") {
					viewModel.signInAction(email: email, keyword: keyword)
				}
				.buttonStyle(.borderedProminent)
				
			}
			.padding()
			.background(Color(.systemGray6))
			.cornerRadius(12)
			.padding()
			
			// 👇 This is what handles the navigation
			.navigationDestination(isPresented: $navigateToHome) {
				HomeView()
			}
			
			Spacer()
			
			HStack {
				Text("Don't have an account?")
				NavigationLink("Sign Up", destination: SignUpView())
			}
			.font(.footnote)
			.padding(.bottom)
		}
		.onReceive(viewModel.$currentState) { newState in
			if newState is UiStateSuccess {
				navigateToHome = true
			}
		}

	}
}

#Preview {
	SignInView()
}

