import React from "react";
import {
  Alert,
  Keyboard,
  KeyboardAvoidingView,
  Platform,
  Pressable,
  SafeAreaView,
  Text,
  TextInput,
  TouchableOpacity,
  TouchableWithoutFeedback,
  View,
} from "react-native";

import { Controller, useForm } from "react-hook-form";

import { SizedBox } from "../components";
import useStyles from "../styles/overall";
import { useDispatch, useSelector } from "react-redux";
import { registerUser } from "../actions/auth";

interface FormData {
  name: string;
  email: string;
  password: string;
  confirmPassword: string;
}

const SignUpForm: React.FC = () => {
  const emailInput = React.useRef<TextInput>(null);
  const passwordInput = React.useRef<TextInput>(null);

  const { loading, userInfo, error, success } = useSelector(
    (state: any) => state.auth
  );
  const dispatch = useDispatch();

  const { control, handleSubmit } = useForm<FormData>({
    defaultValues: {
      name: "",
      email: "",
      password: "",
      confirmPassword: "",
    },
  });

  const onSubmit = handleSubmit((data: FormData) => {
    console.log(data);
    // check if passwords match
    if (data.password !== data.confirmPassword) {
      alert("Password mismatch");
    }
    // transform email string to lowercase to avoid case sensitivity issues in login
    data.email = data.email.toLowerCase();
    //@ts-ignore
    dispatch(registerUser(data));
  });

  const styles = useStyles();

  return (
    <TouchableWithoutFeedback onPress={Keyboard.dismiss}>
      <View style={styles.root}>
        <SafeAreaView style={styles.safeAreaView}>
          <KeyboardAvoidingView
            behavior={Platform.OS === "ios" ? "padding" : "height"}
            style={styles.content}
          >
            <Text style={styles.title}>Welcome to Music Share!</Text>

            <SizedBox height={8} />

            <Text style={styles.subtitle}>Sign Up now to your account</Text>

            <SizedBox height={32} />

            <Pressable onPress={() => emailInput.current?.focus()}>
              <View style={styles.form}>
                <Text style={styles.label}>Name</Text>

                <Controller
                  control={control}
                  name="name"
                  render={({ onBlur, onChange, value }) => (
                    <TextInput
                      autoCapitalize="none"
                      autoCompleteType="username"
                      autoCorrect={false}
                      keyboardType="ascii-capable"
                      onBlur={onBlur}
                      onChangeText={onChange}
                      onSubmitEditing={() => passwordInput.current?.focus()}
                      ref={emailInput}
                      returnKeyType="next"
                      style={styles.textInput}
                      textContentType="givenName"
                      value={value}
                    />
                  )}
                />
              </View>
            </Pressable>
            <SizedBox height={16} />
            <Pressable onPress={() => emailInput.current?.focus()}>
              <View style={styles.form}>
                <Text style={styles.label}>Email</Text>

                <Controller
                  control={control}
                  name="email"
                  render={({ onBlur, onChange, value }) => (
                    <TextInput
                      autoCapitalize="none"
                      autoCompleteType="email"
                      autoCorrect={false}
                      keyboardType="email-address"
                      onBlur={onBlur}
                      onChangeText={onChange}
                      onSubmitEditing={() => passwordInput.current?.focus()}
                      ref={emailInput}
                      returnKeyType="next"
                      style={styles.textInput}
                      textContentType="username"
                      value={value}
                    />
                  )}
                />
              </View>
            </Pressable>
            <SizedBox height={16} />

            <Pressable onPress={() => passwordInput.current?.focus()}>
              <View style={styles.form}>
                <Text style={styles.label}>Password</Text>

                <Controller
                  control={control}
                  name="password"
                  render={({ onBlur, onChange, value }) => (
                    <TextInput
                      autoCapitalize="none"
                      autoCompleteType="password"
                      autoCorrect={false}
                      onBlur={onBlur}
                      onChangeText={onChange}
                      onSubmitEditing={onSubmit}
                      ref={passwordInput}
                      returnKeyType="done"
                      secureTextEntry
                      style={styles.textInput}
                      textContentType="password"
                      value={value}
                    />
                  )}
                />
              </View>
            </Pressable>
            <SizedBox height={16} />

            <Pressable onPress={() => passwordInput.current?.focus()}>
              <View style={styles.form}>
                <Text style={styles.label}>Confirm Password</Text>
                <Controller
                  control={control}
                  name="confirmPassword"
                  render={({ onBlur, onChange, value }) => (
                    <TextInput
                      autoCapitalize="none"
                      autoCompleteType="password"
                      autoCorrect={false}
                      onBlur={onBlur}
                      onChangeText={onChange}
                      onSubmitEditing={onSubmit}
                      ref={passwordInput}
                      returnKeyType="done"
                      secureTextEntry
                      style={styles.textInput}
                      textContentType="password"
                      value={value}
                    />
                  )}
                />
              </View>
            </Pressable>

            <SizedBox height={16} />

            <View style={styles.forgotPasswordContainer}>
              <Text style={styles.textButton}>Forgot password?</Text>
            </View>

            <SizedBox height={16} />

            <TouchableOpacity onPress={onSubmit}>
              <View style={styles.button}>
                <Text style={styles.buttonTitle}>Continue</Text>
              </View>
            </TouchableOpacity>
          </KeyboardAvoidingView>
        </SafeAreaView>
      </View>
    </TouchableWithoutFeedback>
  );
};
export default SignUpForm;
