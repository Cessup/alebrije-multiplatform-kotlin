//
//  ForgotPasswordView.swift
//  iosApp
//
//  Created by Cess UP on 28/09/25.
//

import SwiftUI

struct ForgotPasswordView: View {
	@State private var email = ""
	
	var body: some View {
		VStack(spacing: 20) {
			Text("Reset Password")
				.font(.title2)
				.fontWeight(.semibold)
			
			TextField("Enter your email", text: $email)
				.textFieldStyle(.roundedBorder)
				.keyboardType(.emailAddress)
			
			Button("Continue") {
				// Action here (e.g., reset password request)
			}
			.frame(maxWidth: .infinity)
			.buttonStyle(.borderedProminent)
			
			Spacer()
		}
		.padding()
		.navigationTitle("Forgot Password")
	}
}

#Preview {
	ForgotPasswordView()
}
