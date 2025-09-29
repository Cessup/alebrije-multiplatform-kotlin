//
//  SignInView.swift
//  iosApp
//
//  Created by Cess UP on 28/09/25.
//


import SwiftUI

struct SignInView: View {
	@State private var email = ""
	@State private var password = ""
	
	var body: some View {
		VStack {
			Spacer()
			
			// Card-like container
			VStack(spacing: 16) {
				Text("Sign In")
					.font(.title)
					.fontWeight(.bold)
				
				TextField("Email", text: $email)
					.textFieldStyle(.roundedBorder)
					.keyboardType(.emailAddress)
				
				SecureField("Password", text: $password)
					.textFieldStyle(.roundedBorder)
				
				NavigationLink("Forgot password?", destination: ForgotPasswordView())
					.font(.footnote)
					.frame(maxWidth: .infinity, alignment: .trailing)
				
				NavigationLink("Sign In", destination: HomeView())
					.buttonStyle(.borderedProminent)
			}
			.padding()
			.background(Color(.systemGray6))
			.cornerRadius(12)
			.padding()
			
			Spacer()
			
			// Bottom sign up message
			HStack {
				Text("Don't have an account?")
				NavigationLink("Sign Up", destination: SignUpView())
			}
			.font(.footnote)
			.padding(.bottom)
		}
	}
}

#Preview {
	SignInView()
}
